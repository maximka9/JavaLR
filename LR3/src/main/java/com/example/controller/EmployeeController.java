package com.example.controller;

import com.example.model.Employee;
import com.example.model.EmployeeListWrapper;
import com.example.service.EmployeeService;
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
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Получить список сотрудников, поддерживающий JSON и XML
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getEmployees(@RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = MediaType.APPLICATION_JSON_VALUE) String acceptHeader) {
        List<Employee> employees = employeeService.getAllEmployees();

        // Если запрос на XML, применяем XSLT для преобразования в HTML
        if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
            // Преобразуем сотрудников в XML
            String xmlContent = convertEmployeesToXml(employees);
            String htmlContent = transformXmlToHtml(xmlContent);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "text/html").body(htmlContent);
        }

        // Если запрос на JSON, Spring сам преобразует список сотрудников в JSON
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(employees);
    }

    // Метод для преобразования списка сотрудников в XML с помощью JAXB
    private String convertEmployeesToXml(List<Employee> employees) {
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            EmployeeListWrapper wrapper = new EmployeeListWrapper();
            wrapper.setEmployees(employees);

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
            // Загрузка XSLT шаблона
            StreamSource xslStream = new StreamSource(getClass().getClassLoader().getResourceAsStream("xsl/employee.xsl"));
            StreamSource xmlStream = new StreamSource(new StringReader(xmlContent));
            StringWriter htmlWriter = new StringWriter();

            // Применение XSLT
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslStream);
            transformer.transform(xmlStream, new StreamResult(htmlWriter));

            return htmlWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while transforming XML to HTML", e);
        }
    }

    // Сохранить нового сотрудника
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Обновить сотрудника
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.saveEmployee(employee);
    }

    // Удалить сотрудника
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
