package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roy.Expenses_Management_System.Models.RegesterUser;
import com.roy.Expenses_Management_System.R;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registration_Form extends AppCompatActivity {

    private EditText userName,mobileNo,email,confirmMail;
    private Button usrRegbtn;
    private ProgressBar loadingProgressBar;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    private CircleImageView userImg;
    static int n;
    static String catchMail;
    static int i=1;
    static String sEmail,sPassword;
    static int PReqCode=1;
    static int REQUESCODE=1;
    Uri PickedImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__form);


        userName = findViewById(R.id.rg_user_name);
        mobileNo = findViewById(R.id.rg_user_mob_no);
        email = findViewById(R.id.rg_user_mail);
        confirmMail = findViewById(R.id.rg_conf_mail);
        usrRegbtn = findViewById(R.id.rg_user_btn);
        loadingProgressBar = findViewById(R.id.rg_user_progressBar);
        loadingProgressBar.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance();

        final Intent intent = getIntent();
        final String group_key = intent.getExtras().getString("Group_key");
        final String group_size = intent.getExtras().getString("Group_size");


        // Sender mail credential
        sEmail = "ems.help.foryou@gmail.com";
        sPassword = "ems@007+";


            usrRegbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    loadingProgressBar.setVisibility(View.VISIBLE);
                    usrRegbtn.setVisibility(View.INVISIBLE);

                    final String user_name = userName.getText().toString();
                    final String mobile_no = mobileNo.getText().toString();
                    final String mail = email.getText().toString();
                    final String confirm_mail = confirmMail.getText().toString();
                    final int group_size_int = Integer.parseInt(group_size);
                    n=group_size_int;
                    catchMail = mail.trim();


                    if (!user_name.isEmpty() && !mobile_no.isEmpty() && !mail.isEmpty() && !confirm_mail.isEmpty()) {
                        if (mail.equals(confirm_mail)) {
                            RegesterUser regesterUser = new RegesterUser(user_name, mobile_no, mail, group_key);
                            addUser(regesterUser);
//                            for(int i = 1; i<group_size_int; i++)
//                            {
//                                Intent itself = new Intent(Registration_Form.this,Registration_Form.class);
//                                startActivity(itself);
//                                showMessage("Member"+ i +" added successfully");
//                            }


                        } else {
                            showMessage("Don't match given mail");
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            usrRegbtn.setVisibility(View.VISIBLE);
                        }
                    } else {
                        showMessage("Please fill all the input fields");
                        loadingProgressBar.setVisibility(View.INVISIBLE);
                        usrRegbtn.setVisibility(View.VISIBLE);
                    }
                }
            });


    }

    private void addUser(RegesterUser regesterUser) {
        ref = mDatabase.getReference("Users").push();
        String key = ref.getKey();
        regesterUser.setUser_key(key);

        ref.setValue(regesterUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                sendMailToUser(sEmail,sPassword,catchMail);
                if(i == n)
                {
                    startActivity(new Intent(Registration_Form.this,Login_page.class));
                }
                else
                {
                    showMessage("Member "+ i +" registered successfully");

                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    usrRegbtn.setVisibility(View.VISIBLE);

                    i++;
                }
            }
        });
    }

    private void sendMailToUser(String sEmail, String sPassword, String catchMail) {
        final String senderMail = sEmail.trim();
        final String senderPassword = sPassword.trim();
        long genID = generateRandom(10);
        String receiverMail = catchMail;
        //Initialize properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        //Initialize session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail,senderPassword);
            }
        });

        try
        {
            //Initialize email content
            Message message = new MimeMessage(session);
            //Sender email
            message.setFrom(new InternetAddress(senderMail));
            //Recipient email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverMail));
            //Email Subject
            message.setSubject("Welcome to EMS");
            //Email message
            message.setContent("<div style=\"width:fit-content;margin:auto;font-family: sans-serif;color: white;text-align: center;padding: 20px;\">\n" +
                    "        <div style=\"width: fit-content;padding:40px;background: black;height: 100%;\">\n" +
                    "            <h1 style=\"text-align: center; color: white;\">Welcome to EMS Community</h1><br>\n" +
                    "            <p style=\"text-align: center;color: white;\">Now that you have an account, you're one step closer to login an\n" +
                    "                awesome app</p><br><br>\n" +
                    "                <p style=\"font-family: sans-serif;font-size: 16px;\"><b>Important account information - save for your records.</b></p>\n" +
                    "                <div style=\"margin:10px;padding: 15px;color: white;border:2px solid cyan;margin: auto;text-align: center;\">\n" +
                    "                    <h2>Customer ID</h2>\n" +
                    "                    <h2>"+genID+"</h2>\n" +
                    "                </div><br>\n" +
                    "                <div style=\"padding: 15px;color: white;border: 2px solid cyan;margin: auto;text-align: center;\">\n" +
                    "                    <h2>Password</h2>\n" +
                    "                    <h2>ems@12345</h2>\n" +
                    "                </div><br>\n" +
                    "                <p style=\"color: #f93526;font-family: sans-serif;text-align: left;line-height: 1.6;font-size: 11px;\"><b>Note:-</b><br> This is the user id and password which is use to login EMS. \n" +
                    "                    <br>Please make sure after you login you will be change your password.</p>\n" +
                    "                    \n" +
                    "                \n" +
                    "        </div>\n" +
                    "        <p style=\"color: gray;font-family: sans-serif;text-align: left;line-height: 1.6;font-size: 13px;\">Please do not reply to this email.Emails sent to this address will not be answered.</p>\n" +
                    "        <p style=\"color: gray;font-family: sans-serif;text-align: left;line-height: 1.6;font-size: 13px;\">Copyright &copy; 2020 EMS Community. (U.S.Nagar) Uttarakhand India.All right reserved.</p>\n" +
                    "        \n" +
                    "    </div>","text/html");
            //Send mail
            new SendMail().execute(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private class SendMail extends AsyncTask<Message,String,String> {
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("Success"))
            {
                showMessage("Mail sent please check given mail id..");
                userName.setText(null);
                mobileNo.setText(null);
                email.setText(null);
                confirmMail.setText(null);
            }
            else
            {
                showMessage("Something want wrong");
            }
        }
    }


}
