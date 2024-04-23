package com.example.demo.controller;

import com.example.demo.entity.ValidBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    /**
     * /requesbody    {"name":"@RequestBody方式","age":13}
     *
     * @param validBody
     * @return
     */
    @PostMapping("/valid/requestbody")
    public ValidBody requestBody(@RequestBody @Valid ValidBody validBody) {
        return validBody;
    }
}
