package com.tenseii.parser.controllers;

public class Controller {

    protected String generateSimpleJsonResponse(String fieldName, String fieldValue) {
        return "{" + "\"" + fieldName + "\":" +
                "\"" + fieldValue + "\";}";
    }

    protected String generateSimpleJsonResponse(String fieldName, Long fieldValue) {
        return "{" + "\"" + fieldName + "\":" + fieldValue + ";}";
    }
}
