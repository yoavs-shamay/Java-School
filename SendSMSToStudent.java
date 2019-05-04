import com.telesign.MessagingClient;
import com.telesign.RestClient;

public class SendSMSToStudent {
	public static void sendSMS(int phone,String message) {
		String customerId = "7D3AEBD6-4314-4A0D-B7D6-E839D02B981A";
        String apiKey = "m9v4FlMluBnQAct/I2ggPPRPFm2KVD/2ppMsgPfdpNWaE6mp31m6qvOdquJ1zkEJSgZtJk+V1Ivp7ydNFgMGSw==";
        String phoneNumber = "9720" + phone;
        System.out.println(phoneNumber);
        String messageType = "ARN";

        try {
            MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
            RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
