//package com.Ashutosh.ReportGenerator.Config;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.io.IOException;
//
//public class SimpleGrantedAuthoritySerializer extends JsonSerializer<SimpleGrantedAuthority> {
//    @Override
//    public void serialize(SimpleGrantedAuthority authority, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
//        jsonGenerator.writeString(authority.getAuthority());
//    }
//}
//
//class SimpleGrantedAuthorityDeserializer extends JsonDeserializer<SimpleGrantedAuthority> {
//    @Override
//    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        return new SimpleGrantedAuthority(jsonParser.getValueAsString());
//    }
//}
