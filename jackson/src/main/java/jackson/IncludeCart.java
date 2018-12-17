package jackson;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class IncludeCart {

    @JsonUnwrapped
    Cart customCart;

    String owner;

}