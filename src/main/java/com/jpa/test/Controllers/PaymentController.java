package com.jpa.test.Controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpa.test.Entities.User;
import com.jpa.test.Services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
    @Autowired
    UserService service;

    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
        Order order = null;
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_A9p6rhUUEHx94W", "ujE59291HJ9ZYb0RjeONCJoD");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 10000);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            orderRequest.put("notes", notes);

            order = razorpay.orders.create(orderRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating order: " + e.getMessage());
        }
        return order != null ? order.toString() : "{}";
    }

    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
                                 @RequestParam String signature) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_A9p6rhUUEHx94W", "ujE59291HJ9ZYb0RjeONCJoD");
            String verificationData = orderId + "|" + paymentId;
            return Utils.verifySignature(verificationData, signature, "ujE59291HJ9ZYb0RjeONCJoD");
        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("payment-success")
    public String paymentSuccess(HttpSession session) {
        String email = (String) session.getAttribute("email");
        User user = service.getUser(email);
        if (user != null) {
            user.setPremium(true);
            service.updateUser(user, email);
        }
        return "login";
    }

    @GetMapping("payment-failure")
    public String paymentFailure() {
        return "login";
    }
}
