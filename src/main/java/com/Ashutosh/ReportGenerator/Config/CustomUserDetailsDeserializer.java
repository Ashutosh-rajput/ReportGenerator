//package com.Ashutosh.ReportGenerator.Config;
//
//import com.Ashutosh.ReportGenerator.Config.CustomUserDetails;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class CustomUserDetailsDeserializer extends JsonDeserializer<CustomUserDetails> {
//
//    @Override
//    public CustomUserDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
//        JsonNode root = mapper.readTree(jsonParser);
//
//        String username = root.get("username").asText();
//        String password = root.get("password").asText();
//        boolean accountNonExpired = root.get("accountNonExpired").asBoolean();
//        boolean accountNonLocked = root.get("accountNonLocked").asBoolean();
//        boolean credentialsNonExpired = root.get("credentialsNonExpired").asBoolean();
//        boolean enabled = root.get("enabled").asBoolean();
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        Iterator<JsonNode> elements = root.get("authorities").elements();
//        while (elements.hasNext()) {
//            JsonNode authorityNode = elements.next();
//            authorities.add(new SimpleGrantedAuthority(authorityNode.get("authority").asText()));
//        }
//
//        CustomUserDetails userDetails = new CustomUserDetails(username, password, authorities);
//        userDetails.setAccountNonExpired(accountNonExpired);
//        userDetails.setAccountNonLocked(accountNonLocked);
//        userDetails.setCredentialsNonExpired(credentialsNonExpired);
//        userDetails.setEnabled(enabled);
//
//        return userDetails;
//    }
//}
