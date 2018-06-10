package com.kcell.exercises;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents/")
public class DocumentController {

    private static final String SELECT = "Select LAST(value),\"value\" from docs";

    @Value("${spring.influxdb.database}")
    private String database;

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;
    private InfluxDBResultMapper mapper = new InfluxDBResultMapper();

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    public Document getLastDocument() {
        List<Document> documents = mapper.toPOJO(influxDBTemplate.query(new Query(SELECT, database)), Document.class);
        return documents.stream()
                .findFirst()
                .orElse(Document.DEFAULT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/last")
    public Document getLastDocument(@RequestBody Document document) {
        influxDBTemplate.write(Point.measurement("docs")
                .addField("value", document.getValue())
                .build());
        return getLastDocument();
    }
}
