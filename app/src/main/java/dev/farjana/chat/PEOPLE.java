package dev.farjana.chat;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farjana on 1/11/2018.
 */

public class PEOPLE extends Fragment {

    View view1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @SuppressLint({"NotifyDataSetChanged", "Range"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view1 = inflater.inflate(R.layout.peoplelayout, container, false);
        recyclerView = view1.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        Contact a;
        List<Contact> data = new ArrayList<>();

        ContentResolver contentResolver = requireActivity().getContentResolver();
        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC") ;
            if (cursor.getCount() >0) {
                while (cursor.moveToNext()) {

                   int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    if (hasPhoneNumber > 0) {
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        int imageId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                        a = new Contact();
                        a.setNameTxt(name);
                        a.setImageId(imageId);

                        Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id},
                                null);

                        try {
                            if (phoneCursor.moveToNext()) {
                               String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                                a.setContactNumber(phoneNumber);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        phoneCursor.close();

                        @SuppressLint("Recycle") Cursor emailCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (emailCursor.moveToNext()) {
                            String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        }

/*

                        Cursor imagecursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id},
                                null);

                        try {
                            if (imagecursor.moveToNext()) {
                                imageId = Integer.parseInt(imagecursor.getString(imagecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID)));
                                Toast.makeText(getContext(), image, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        imagecursor.close();*/


                        data.add(a);


                    }
                }
            }

            recyclerView.setLayoutManager(layoutManager);
            adapter = new PeopleAdapter(data, getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

            return view1;

        }
}