/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

/**
 *
 * @author Dhake
 */
public class SMS {
    public static void SendSMS(String FROM , String messageText , String to  )
    {
   NexmoClient client = new NexmoClient.Builder()
  .apiKey("1ccc34cf")
  .apiSecret("x6F6zbwLEZroD9A5")
  .build();
TextMessage message = new TextMessage(FROM, to , messageText);
SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
}
}
    

