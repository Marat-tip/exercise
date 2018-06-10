package com.kcell.exercises;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "docs")
public class Document {
    public static Document DEFAULT = new Document(0);

    @Column(name = "value")
    private Integer value;

    @Column(name = "time")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant time;

    public Document() {
    }

    public Document(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Instant getTime() {
        return time;
    }
}
