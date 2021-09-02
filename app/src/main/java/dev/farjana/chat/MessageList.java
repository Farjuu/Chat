package dev.farjana.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.farjana.chat.MessageData.model.Messages;
import dev.farjana.chat.MessageData.remote.APIService;
import dev.farjana.chat.MessageData.remote.ApiUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MessageList extends AppCompatActivity {

    private static final String TAG ="" ;
    RecyclerView mMessageRecycler;
    MessageListAdapter mMessageAdapter;
    final List<BaseMessage> messageList = new ArrayList<>();
    static APIService mAPIService;

    CircleImageView senderImage;
    TextView senderName;
    Intent i;
    EditText editText;
    ImageButton imageButton;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        initializeAll();

        i = getIntent();

        senderName.setText(i.getStringExtra("people.name"));
       // senderImage.setImageResource(Integer.parseInt(i.getStringExtra("people.imageId")));

        mMessageRecycler =  findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        imageButton.setOnClickListener(view -> {
            if(!(TextUtils.isEmpty(editText.getText().toString().trim()))) {
                sendMessage(editText.getText().toString(), i.getStringExtra("people.number"));
                messageList.add(new BaseMessage(getCurrTime(), editText.getText().toString(), true));
                mMessageAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        mMessageAdapter = new MessageListAdapter(messageList,this);
        mMessageRecycler.setAdapter(mMessageAdapter);
    }

    private void initializeAll() {
        senderImage = findViewById(R.id.imgId);
        senderName = findViewById(R.id.sender);
        editText = findViewById(R.id.edittext_chatbox);
        imageButton = findViewById(R.id.button_chatbox_send);


    }

    private void sendMessage(String message, String number) {

        mAPIService = ApiUtils.getAPIService();
        mAPIService.sendMessage(message, number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Messages>() {

                    @Override
                    public void onCompleted() {
                        //   Toast.makeText(getBaseContext(),"on oncomplete method",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Toast.makeText(getBaseContext(),"on onerror method",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Messages message) {
                        //  Toast.makeText(getBaseContext(),"on ONNEXT method",Toast.LENGTH_LONG).show();
                        showResponse(message.toString());
                    }
                });
    }

    private void showResponse(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        Log.i(TAG,"message gone....");
    }

    private String getCurrTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

}
