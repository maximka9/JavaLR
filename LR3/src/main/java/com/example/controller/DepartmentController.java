package com.example.controller;

import com.example.model.Department;
import com.example.model.DepartmentListWrapper;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Получить список департаментов, поддерживающий JSON и XML
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getDepartments(@RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String acceptHeader) {
        List<Department> departments = departmentService.getAllDepartments();

        // Если запрос на XML, применяем XSLT для преобразования в HTML
        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            String xmlContent = convertDepartmentsToXml(departments);
            String htmlContent = transformXmlToHtml(xmlContent);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "text/html").body(htmlContent);
        }

        // Если запрос на JSON, Spring сам преобразует список департаментов в JSON
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(departments);
    }

    // Метод для преобразования списка департаментов в XML
    private String convertDepartmentsToXml(List<Department> departments) {
        try {
            JAXBContext context = JAXBContext.newInstance(DepartmentListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            DepartmentListWrapper wrapper = new DepartmentListWrapper();
            wrapper.setDepartments(departments);

            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);

            // Логирование XML
            System.out.println("Generated XML: " + writer.toString());

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while converting to XML", e);
        }
    }

    // Метод для применения XSLT трансформации XML в HTML
    private String transformXmlToHtml(String xmlContent) {
        try {
            StreamSource xslStream = new StreamSource(getClass().getClassLoader().getResourceAsStream("xsl/department.xsl"));
            StreamSource xmlStream = new StreamSource(new StringReader(xmlContent));
            StringWriter htmlWriter = new StringWriter();

            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslStream);
            transformer.transform(xmlStream, new StreamResult(htmlWriter));

            return htmlWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while transforming XML to HTML", e);
        }
    }

    // Сохранить новый департамент
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    // Обновить департамент
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setId(id);
        return departmentService.saveDepartment(department);
    }

    // Удалить департамент
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
