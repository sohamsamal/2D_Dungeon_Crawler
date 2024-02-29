package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.model.DefaultMovement;
import com.example.myapplication.model.Leaderboard;
import com.example.myapplication.viewmodel.PlayerViewModel;

public class ConfigActivity extends AppCompatActivity {

    private PlayerViewModel playerVM;
    private Leaderboard leaderboard;

    private static double difficulty;
    private String playerName;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button submitBtn = findViewById(R.id.submitButton);
        nameEditText = findViewById(R.id.nameEditText);
        ViewPager2 spriteViewPager = findViewById(R.id.spriteViewPager);
        spriteViewPager.setAdapter(new SpriteAdapter());
        playerVM = PlayerViewModel.getPlayerVM();
        leaderboard = Leaderboard.getLeaderboard();
        int score = 100;

        submitBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            difficulty = 1;

            switch (difficultyRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioEasy:
                difficulty = 1;
                break;
            case R.id.radioNormal:
                difficulty = 0.75;
                break;
            case R.id.radioHard:
                difficulty = 0.5;
                break;
            default:
                difficulty = 1;
                break;
            }
            playerVM.setHealthVM(playerVM.getHealthVM() * difficulty);
            playerName = nameEditText.getText().toString().trim();

            if (TextUtils.isEmpty(playerName)) {
                Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            } else {
                int selectedSprite = spriteViewPager.getCurrentItem();

                playerVM.setNameVM(playerName);
                DefaultMovement dMove = new DefaultMovement(playerVM.getPlayer());
                playerVM.setMovementStrategy(dMove);

                // Creating bundled data to send to next screen
                Bundle data = new Bundle();
                data.putDouble("difficulty", difficulty);
                data.putString("player_name", playerName);
                data.putDouble("health", playerVM.getHealthVM());
                data.putInt("sprite", selectedSprite);
                data.putInt("score", score);

                Intent intent = new Intent(ConfigActivity.this, Room1Activity.class);
                intent.putExtras(data);
                finish();
                startActivity(intent);
            }
        });
    }
    public static double getDifficulty() {
        return difficulty;
    }
    private static class SpriteAdapter extends
            RecyclerView.Adapter<SpriteAdapter.SpriteViewHolder> {
        private final int[] spriteImages = {
            R.drawable.blue_char_transparent,
            R.drawable.red_char_transparent,
            R.drawable.white_char_transparent
        }; // all the same for now

        public int getImages(int index) {
            return spriteImages[index];
        }

        @NonNull
        @Override
        public SpriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());

            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            return new SpriteViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull SpriteViewHolder holder, int position) {
            holder.imageView.setImageResource(spriteImages[position]);
        }

        @Override
        public int getItemCount() {
            return spriteImages.length;
        }

        private static class SpriteViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            SpriteViewHolder(@NonNull ImageView itemView) {
                super(itemView);
                this.imageView = itemView;
            }
        }
    }
}
