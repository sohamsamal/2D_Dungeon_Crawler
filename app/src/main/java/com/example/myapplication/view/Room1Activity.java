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
import com.example.myapplication.model.FactoryOne;
import com.example.myapplication.model.HealthManager;
import com.example.myapplication.model.Obstacle;
import com.example.myapplication.model.Player;
import com.example.myapplication.model.Renderer;
import com.example.myapplication.model.ScoreManager;
import com.example.myapplication.model.SpeedMovement;
import com.example.myapplication.model.SpriteSelector;
import com.example.myapplication.viewmodel.EnemyViewModel;
import com.example.myapplication.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class Room1Activity extends AppCompatActivity implements Enemy.OnPlayerHealthChangeListener {
    private TextView score;
    private ScoreManager scoreManager;
    private HealthManager healthManager;
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
    private TextView powerUpText;

    private static final int POWERUPX = 100;
    private static final int POWERUPY = 850;

    private ImageView strengthPowerUp;

    private ImageView directionIndicator;
    private Handler slashSpriteHandler = new Handler(Looper.getMainLooper());
    private ImageView slashSprite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen1);
        ImageView characterSprite = findViewById(R.id.characterSprite);
        score = findViewById(R.id.scoreText);
        healthBar = findViewById(R.id.healthBar);
        updateHealthBar();

        ScoreManager.setScore(100);
        spriteSelector = new SpriteSelector();
        playerVM = PlayerViewModel.getPlayerVM();
        playerVM.setPositionVM(0, 0);

        EnemyFactory factoryOne = new FactoryOne();
        enemyList = factoryOne.createEnemyList();
        enemyList.get(0).updatePosition(600, 500);
        enemyList.get(1).updatePosition(100, 1200);

        ImageView enemySpriteOne = findViewById(R.id.monster_1);
        if (enemySpriteOne != null) {
            Renderer rendererEnemyOne = new Renderer(enemySpriteOne);
            enemyList.get(0).addObserver(rendererEnemyOne);
        }

        ImageView enemySpriteTwo = findViewById(R.id.monster_2);
        if (enemySpriteTwo != null) {
            Renderer rendererEnemyTwo = new Renderer(enemySpriteTwo);
            enemyList.get(1).addObserver(rendererEnemyTwo);
        }

        strengthPowerUp = findViewById(R.id.strength_powerup);
        strengthPowerUp.setVisibility(View.VISIBLE);

        powerUpText = findViewById(R.id.PowerUpText);

        scoreManager = new ScoreManager();  // Initial score value
        spriteSelector = new SpriteSelector();
        playerVM = PlayerViewModel.getPlayerVM();
        SpeedMovement sMove = new SpeedMovement(playerVM.getPlayer());
        playerVM.setMovementStrategy(sMove);

        playerVM.setPositionVM(0, 0);

        slashSprite = findViewById(R.id.slash);
        if (slashSprite != null) {
            Renderer rendererSlash = new Renderer(slashSprite);
            playerVM.getWeaponVM().addObserver(rendererSlash);
        }
        enemyVM1 = EnemyViewModel.getEnemyVM(enemyList.get(0));
        for (Enemy enemy : enemyList) {
            enemy.setOnPlayerHealthChangeListener(this);
        }
        enemyVM2 = EnemyViewModel.getEnemyVM(enemyList.get(1));
        ImageView wall1 = findViewById(R.id.wall1);
        ImageView wall2 = findViewById(R.id.wall2);

        directionIndicator = findViewById(R.id.indicator);

        wall1.post(new Runnable() {
            @Override
            public void run() {
                obstacles.add(new Obstacle(wall1.getLeft(),
                        wall1.getTop(), wall1.getWidth(), wall1.getHeight()));
            }
        });
        wall2.post(new Runnable() {
            @Override
            public void run() {
                obstacles.add(new Obstacle(wall2.getLeft(),
                        wall2.getTop(), wall2.getWidth(), wall2.getHeight()));
            }
        });
        Bundle data = getIntent().getExtras();
        int index = data.getInt("sprite");
        characterSprite.setImageResource(spriteSelector.getSpriteImage(index));
        updateScoreDisplay();
        startScoreUpdate();
        renderer = new Renderer(characterSprite);
        playerVM = PlayerViewModel.getPlayerVM();
        playerVM.addObserver(renderer);
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

    public void onPlayerHealthChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Player.getPlayer().getHealth() <= 0) {
                    Intent intent = new Intent(Room1Activity.this, EndingScreen.class);
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

    private void moveToNextScreen() {
        Intent intent = new Intent(Room1Activity.this, Room2Activity.class);
        Bundle data = getIntent().getExtras();
        data.putDouble("score", scoreManager.getScore());
        intent.putExtras(data);
        finish();
        startActivity(intent);
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

    private void updateScoreDisplay() {
        score.setText("Score: " + scoreManager.getScore());
    }


    private boolean checkPlayerReachedTarget() {
        float targetX = 795;
        float targetY = 1630;

        return playerVM.getXVM() >= targetX && playerVM.getYVM() >= targetY;
    }

    private boolean collected;

    // handle key events for player movement
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
            }, 500); // 1000 milliseconds = 1 second

            break;

        default:
            break;
        }
      
        if ((playerVM.getYVM() < POWERUPY + 20 && playerVM.getYVM() > POWERUPY - 20)
                && !collected) {
            strengthPowerUp.setVisibility(View.INVISIBLE);
            powerUpText.append(" Strength Increased!");
            scoreManager.incrementScore(5);
            playerVM.getPlayer().setHealth(playerVM.getPlayer().getHealth() + 15);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scoreHandler.removeCallbacksAndMessages(null);
        enemyMovementHandler.removeCallbacksAndMessages(null);
    }
}

