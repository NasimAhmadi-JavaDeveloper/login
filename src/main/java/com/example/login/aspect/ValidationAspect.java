//package com.example.login.aspect;
//
//import com.example.login.exception.ExceptionSpec;
//import com.example.login.exception.LogicalException;
//import com.example.login.model.request.UserRequest;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.fge.jsonpatch.JsonPatch;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import java.util.Set;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class ValidationAspect {
//    private final Validator validator;
//    private final ObjectMapper objectMapper;
//
//    @Before(value = "execution(* com.example.login.service.UserService.updateUser(..)) && args(id, request)", argNames = "id,request")
//    public void validateUserRequest(Integer id, JsonPatch request) {
//        UserRequest userRequest = convertJsonPatchToUserRequest(request);
//
//        // Validate UserRequest
//        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
//        if (!violations.isEmpty()) {
//            StringBuilder errorMessage = new StringBuilder();
//            for (ConstraintViolation<UserRequest> violation : violations) {
//                errorMessage.append(violation.getMessage()).append("; ");
//            }
//            throw new LogicalException(ExceptionSpec.VALIDATE);
//        }
//    }
//
//    private UserRequest convertJsonPatchToUserRequest(JsonPatch request) {
//        UserRequest userRequest = new UserRequest();
//        try {
//            JsonNode userJsonNode = objectMapper.convertValue(userRequest, JsonNode.class);
//            JsonNode patchedNode = request.apply(userJsonNode);
//            userRequest = objectMapper.treeToValue(patchedNode, UserRequest.class);
//        } catch (Exception e) {
//            throw new LogicalException(ExceptionSpec.VALIDATE);
//        }
//
//        return userRequest;
//    }
//}
