package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Enemy;
import com.example.myapplication.model.EnemyFactory;
import com.example.myapplication.model.FactoryTwo;
import com.example.myapplication.model.Obstacle;
import com.example.myapplication.model.Player;
import com.example.myapplication.model.Renderer;
import com.example.myapplication.model.ScoreManager;
import com.example.myapplication.model.SpeedMovement;
import com.example.myapplication.model.SpriteSelector;
import com.example.myapplication.model.Sword;
import com.example.myapplication.viewmodel.EnemyViewModel;
import com.example.myapplication.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class Room2Activity extends AppCompatActivity implements Enemy.OnPlayerHealthChangeListener {
    private TextView score;
    private ScoreManager scoreManager;
    private SpriteSelector spriteSelector;
    private Handler scoreHandler = new Handler(Looper.getMainLooper());
    private PlayerViewModel playerVM;
    private EnemyViewModel enemyVM1;
    private EnemyViewModel enemyVM2;
    private Renderer renderer;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Handler enemyMovementHandler = new Handler(Looper.getMainLooper());
    private List<Enemy> enemyList = new ArrayList<>();
    private ProgressBar healthBar;

    private static final int POWERUPX = 100;
    private static final int POWERUPY = 850;

    private ImageView healthPowerUp;

    private TextView powerUpText;
    private ImageView directionIndicator;
    private Handler slashSpriteHandler = new Handler(Looper.getMainLooper());
    private ImageView slashSprite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen2);
        Bundle data = getIntent().getExtras();
        ImageView characterSprite = findViewById(R.id.characterSprite);
        score = findViewById(R.id.scoreText);
        // testPos = findViewById(R.id.posText);
        healthBar = findViewById(R.id.healthBar);
        updateHealthBar();

        EnemyFactory factoryTwo = new FactoryTwo();
        enemyList = factoryTwo.createEnemyList();
        enemyList.get(0).updatePosition(600, 500); // Example positions
        enemyList.get(1).updatePosition(100, 1200);

        ImageView enemySpriteOne = findViewById(R.id.monster_1);
        Renderer rendererEnemyOne = enemyList.get(0).setRenderer(enemySpriteOne);
        enemyList.get(0).addObserver(rendererEnemyOne);

        ImageView enemySpriteTwo = findViewById(R.id.monster_2);
        Renderer rendererEnemyTwo = enemyList.get(1).setRenderer(enemySpriteTwo);
        enemyList.get(1).addObserver(rendererEnemyTwo);

        scoreManager = new ScoreManager();  // Initial score value
        spriteSelector = new SpriteSelector();
        playerVM = PlayerViewModel.getPlayerVM();
        SpeedMovement sMove = new SpeedMovement(playerVM.getPlayer());
        playerVM.setMovementStrategy(sMove);

        playerVM.setPositionVM(0, 0);

        playerVM.setWeaponVM(new Sword(10));
        slashSprite = findViewById(R.id.slash);
        if (slashSprite != null) {
            Renderer rendererSlash = new Renderer(slashSprite);
            playerVM.getWeaponVM().addObserver(rendererSlash);
        }

        ImageView wall1 = findViewById(R.id.wall1);
        ImageView wall2 = findViewById(R.id.wall2);

        healthPowerUp = findViewById(R.id.health_powerup);
        healthPowerUp.setVisibility(View.VISIBLE);

        powerUpText = findViewById(R.id.PowerUpText);
        directionIndicator = findViewById(R.id.indicator);

        wall1.post(new Runnable() {
            @Override
            public void run() {
                obstacles.add(new Obstacle(wall1.getLeft(), wall1.getTop(),
                        wall1.getWidth(), wall1.getHeight()));
            }
        });
        wall2.post(new Runnable() {
            @Override
            public void run() {
                obstacles.add(new Obstacle(wall2.getLeft(), wall2.getTop(),
                        wall2.getWidth(), wall2.getHeight()));
            }
        });

        int index = data.getInt("sprite");
        characterSprite.setImageResource(spriteSelector.getSpriteImage(index));
        updateScoreDisplay();
        startScoreUpdate();

        renderer = new Renderer(characterSprite);
        playerVM = PlayerViewModel.getPlayerVM();
        playerVM.addObserver(renderer);

        enemyVM1 = EnemyViewModel.getEnemyVM(enemyList.get(0));
        //enemyVM1.setPositionVM(30, 500);

        for (Enemy enemy : enemyList) {
            enemy.setOnPlayerHealthChangeListener(this);
        }
        enemyVM2 = EnemyViewModel.getEnemyVM(enemyList.get(1));

        characterSprite.post(new Runnable() {
            @Override
            public void run() {
                int spriteWidth = characterSprite.getWidth();
                int spriteHeight = characterSprite.getHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;
                int screenWidth = displayMetrics.widthPixels;

                Player player = Player.getPlayer();
                player.setDimensions(screenWidth, screenHeight,
                        spriteWidth, spriteHeight); // Set the dimensions here
            }
        });
        slashSprite.post(new Runnable() {
            @Override
            public void run() {
                int spriteWidth = slashSprite.getWidth();
                int spriteHeight = slashSprite.getHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;
                int screenWidth = displayMetrics.widthPixels;

                playerVM.getPlayer().getWeapon().setDimensions(screenWidth, screenHeight,
                        spriteWidth, spriteHeight);

            }
        });
        enemySpriteOne.post(new Runnable() {
            @Override
            public void run() {
                int spriteWidth = enemySpriteOne.getWidth();
                int spriteHeight = enemySpriteOne.getHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;
                int screenWidth = displayMetrics.widthPixels;

                Enemy enemyOne = enemyList.get(0);
                enemyOne.setDimensions(screenWidth, screenHeight,
                        spriteWidth, spriteHeight); // Set the dimensions here
                enemyOne.updatePositionVM(obstacles, playerVM.getPlayer());
                enemyMovementHandler.postDelayed(this, 100);
            }
        });
        enemySpriteTwo.post(new Runnable() {
            @Override
            public void run() {
                int spriteWidth = enemySpriteTwo.getWidth();
                int spriteHeight = enemySpriteTwo.getHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;
                int screenWidth = displayMetrics.widthPixels;

                Enemy enemyTwo = enemyList.get(1);
                enemyTwo.setDimensions(screenWidth, screenHeight,
                        spriteWidth, spriteHeight); // Set the dimensions here
                enemyTwo.updatePositionVM(obstacles, playerVM.getPlayer());
                enemyMovementHandler.postDelayed(this, 100);
            }
        });
    }



    private void startScoreUpdate() {
        scoreHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scoreManager.decrementScore(1);
                updateScoreDisplay();
                if (scoreManager.getScore() > 0) {
                    scoreHandler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }


    private void moveToNextScreen() {
        Intent intent = new Intent(Room2Activity.this, Room3Activity.class);
        Bundle data = getIntent().getExtras();
        data.putDouble("score", scoreManager.getScore());
        intent.putExtras(data);
        finish();
        startActivity(intent);
    }

    private void updateScoreDisplay() {
        score.setText("Score: " + scoreManager.getScore());
    }


    // handle key events for player movement

    private boolean collected;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_UP:
            playerVM.updatePositionVM("up", obstacles);
            updateIndicator(playerVM.getXVM() + 120, playerVM.getYVM() - 17);
            playerVM.setOrientationVM(0);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            playerVM.updatePositionVM("down", obstacles);
            updateIndicator(playerVM.getXVM() + 120, playerVM.getYVM() + 217);
            playerVM.setOrientationVM(1);
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            playerVM.updatePositionVM("left", obstacles);
            updateIndicator(playerVM.getXVM() - 2, playerVM.getYVM() + 120);
            playerVM.setOrientationVM(2);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            playerVM.updatePositionVM("right", obstacles);
            updateIndicator(playerVM.getXVM() + 217, playerVM.getYVM() + 120);
            playerVM.setOrientationVM(3);
            break;
        case KeyEvent.KEYCODE_SPACE:
            int axis = playerVM.getOrientationVM();

            if (axis == 0) {
                playerVM.getWeaponVM().updatePosition(enemyList,
                        playerVM.getXVM() + 100, playerVM.getYVM() - 77);
            } else if (axis == 1) {
                playerVM.getWeaponVM().updatePosition(enemyList,
                        playerVM.getXVM() + 100, playerVM.getYVM() + 217);
            } else if (axis == 2) {
                playerVM.getWeaponVM().updatePosition(enemyList,
                        playerVM.getXVM() - 52, playerVM.getYVM() + 80);
            } else {
                playerVM.getWeaponVM().updatePosition(enemyList,
                        playerVM.getXVM() + 217, playerVM.getYVM() + 80);
            }
            slashSprite.setVisibility(View.VISIBLE); // Make the slash sprite visible

            // Schedule the slash sprite to disappear after 1 second (1000 milliseconds)
            slashSpriteHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    slashSprite.setVisibility(View.INVISIBLE); // Hide the slash sprite
                }
            }, 200); // 1000 milliseconds = 1 second

            break;

        default:
            break;
        }
        // updatePosDisplay();

        if ((playerVM.getYVM() < POWERUPY + 20 && playerVM.getYVM() > POWERUPY - 20)
                && !collected) {
            healthPowerUp.setVisibility(View.INVISIBLE);
            powerUpText.append(" Health Increased!");
            playerVM.getPlayer().setHealth(playerVM.getPlayer().getHealth() + 20);
            updateHealthBar();
            updateScoreDisplay();
            collected = !collected;
        }

        if (checkPlayerReachedTarget()) {
            moveToNextScreen();
            return true;
        }

        return true;
    }

    private void removeDestroyedEnemies() {
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            if (enemyList.get(i).isDestroyed()) {
                enemyList.remove(i);
            }
        }
    }

    public void updateIndicator(float x, float y) {
        directionIndicator.setX(x);
        directionIndicator.setY(y);
    }

    private boolean checkPlayerReachedTarget() {
        float targetX = 795;
        float targetY = 1630;

        return playerVM.getXVM() >= targetX && playerVM.getYVM() >= targetY;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scoreHandler.removeCallbacksAndMessages(null);
        enemyMovementHandler.removeCallbacksAndMessages(null);
    }


    public void onPlayerHealthChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Player.getPlayer().getHealth() <= 0) {
                    Intent intent = new Intent(Room2Activity.this, EndingScreen.class);
                    Bundle data = getIntent().getExtras();
                    data.putDouble("score", scoreManager.getScore());
                    intent.putExtras(data);
                    finish();
                    startActivity(intent);
                } else {
                    updateHealthBar();
                }
            }
        });
    }



    private void updateHealthBar() {
        int currentHealth = (int) Player.getPlayer().getHealth(); // Get current health
        healthBar.setProgress(currentHealth); // Update the progress bar
    }

}
