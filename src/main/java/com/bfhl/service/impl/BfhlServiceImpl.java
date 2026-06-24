package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_FULL_NAME = "john_doe";
    private static final String DOB            = "01011990";
    private static final String EMAIL          = "john.doe@example.com";
    private static final String ROLL_NUMBER    = "ROLL001";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers   = new ArrayList<>();
        List<String> evenNumbers  = new ArrayList<>();
        List<String> alphabets    = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) evenNumbers.add(item);
                else               oddNumbers.add(item);
            } else if (item.length() == 1 && Character.isLetter(item.charAt(0))) {
                alphabets.add(item.toUpperCase());
            } else {
                specialChars.add(item);
            }
        }

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_FULL_NAME + "_" + DOB);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);
        response.setSum(String.valueOf(sum));
        response.setConcatString(buildConcatString(alphabets));

        return response;
    }

    private boolean isNumber(String s) {
        try { Long.parseLong(s); return true; }
        catch (NumberFormatException e) { return false; }
    }

    private String buildConcatString(List<String> alphabets) {
        List<String> reversed = new ArrayList<>(alphabets);
        Collections.reverse(reversed);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            String ch = reversed.get(i);
            sb.append(i % 2 == 0 ? ch.toUpperCase() : ch.toLowerCase());
        }
        return sb.toString();
    }
}
