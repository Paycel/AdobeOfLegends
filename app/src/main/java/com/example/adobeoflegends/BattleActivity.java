package com.example.adobeoflegends;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adobeoflegends.Battle;
import com.example.adobeoflegends.R;
import com.google.android.material.resources.TextAppearance;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_battle);
        final Battle battle = new Battle();
        final ConstraintLayout playerDeckLayout = (ConstraintLayout) findViewById(R.id.player_deck);

        ViewTreeObserver observer = playerDeckLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                playerDeckLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                for (int i = 0; i < Battle.numsOfCards; i++)
                 createCardsViews(playerDeckLayout, battle, i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:
                Toast.makeText(this, "CARD 1 CLICKED", Toast.LENGTH_SHORT).show();
                break;
        }
        Toast.makeText(this, "??? CLICKED", Toast.LENGTH_SHORT).show();

    }

    // отчаянная попытка создать вручную, но неудачно, а иначе и никак
    void createCardsViews(ConstraintLayout playerDeck, Battle battle, int i){
        // width -> height
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams l_allWrap_Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        l_allWrap_Params.setMargins(0, 0, 0, 0);
        FrameLayout.LayoutParams flParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        CardView.LayoutParams cvParams = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        cvParams.setMargins(0, 20, 0, 0);
        LinearLayout card = (LinearLayout) findViewById(R.id.card2);
        switch (i){
            case 0: card = (LinearLayout) findViewById(R.id.card1); break;
            case 1: card = (LinearLayout) findViewById(R.id.card2); break;
            case 2: card = (LinearLayout) findViewById(R.id.card3); break;
            case 3: card = (LinearLayout) findViewById(R.id.card4); break;
        }

        FrameLayout fl = new FrameLayout(card.getContext());
        fl.setLayoutParams(flParams);

        CardView cv = new CardView(fl.getContext());
        cv.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        cv.setLayoutParams(cvParams);
        cv.setOnClickListener(this);

        LinearLayout ll = new LinearLayout(cv.getContext());
        ll.setGravity(Gravity.CENTER);
        ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
        ll.setPadding(5, 0, 0, 0);
        ll.setClickable(true);
        ll.setEnabled(true);
        ll.setOnClickListener(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(lParams);

        ImageView image = new ImageView(ll.getContext());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(150, 250);
        image.setLayoutParams(imageParams);
        image.setImageResource(battle.player.cardList.get(i).pictureID);
        image.setScaleType(ImageView.ScaleType.FIT_START);

        TextView stats = new TextView(ll.getContext());
        stats.setLayoutParams(l_allWrap_Params);
        stats.setGravity(Gravity.CENTER);
        stats.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        stats.setText(battle.player.cardList.get(i).damagePoints + " " + battle.player.cardList.get(i).healthPoints);

        TextView desc = new TextView(ll.getContext());
        l_allWrap_Params.setMargins(0, 15, 0, 8);
        desc.setLayoutParams(l_allWrap_Params);
        desc.setGravity(Gravity.CENTER);
        desc.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
        desc.setText(battle.player.cardList.get(i).name);

        card.addView(fl);
        fl.addView(cv);
        cv.addView(ll);
        ll.addView(image);
        ll.addView(stats);
        ll.addView(desc);

//        rv1.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(card.getContext());
//        rv1.setLayoutManager(llm);
//        RVAdapter adapter = new RVAdapter(battle.player.cardList, i);
//        rv1.setAdapter(adapter);


        //        {
//            // Card stats (HP and Mana)
//            LinearLayout lstats = new LinearLayout(card.getContext());
//            lstats.setOrientation(LinearLayout.VERTICAL);
//            // change to 4 layouts
//            TextView stats = new TextView(lstats.getContext());
//            String statsString = cards[i].damagePoints + " " + cards[i].healthPoints;
//            stats.setText(statsString);
//            stats.setGravity(Gravity.CENTER);
//            lstats.addView(stats, layoutParams);
//
//            // Card description
//            LinearLayout ldescription = new LinearLayout(card.getContext());
//            ldescription.setOrientation(LinearLayout.VERTICAL);
//            TextView description = new TextView(ldescription.getContext());
//            description.setText(cards[i].name);
//            description.setGravity(Gravity.CENTER);
//            ldescription.addView(description, layoutParams);
//
//            // union to main layout
//            card.addView(image);
//            card.addView(lstats);
//            card.addView(ldescription);
//        }
    }


}
