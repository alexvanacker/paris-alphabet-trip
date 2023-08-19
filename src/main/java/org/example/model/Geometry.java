package org.example.model;

/**
 * "type":"Feature","geometry":{"type":"Point","coordinates":[2.34723336,48.870426475]},"properties":{"name":"SEF3 / SA4D","context":"Paris, Paris","citycode":"75056","depcode":"75"}}]}
 */


public record Geometry(String type, double[] coordinates) {

}
