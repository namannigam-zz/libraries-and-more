package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CartConsumer {

    public static void main(String[] args) {
        IncludeCart includeCart = new IncludeCart();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.convertValue(includeCart, IncludeCart.class);

        System.out.println(objectMapper.toString());
    }
}