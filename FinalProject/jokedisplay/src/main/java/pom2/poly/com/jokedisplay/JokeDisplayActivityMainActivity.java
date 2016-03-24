package pom2.poly.com.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivityMainActivity extends AppCompatActivity {
    public static final String GET_JOKE_KEY = "this is a joke key";

    private TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_display_activity_main);
        tvJoke = (TextView) findViewById(R.id.tvJoke);
        String joke = getIntent().getStringExtra(GET_JOKE_KEY);
        tvJoke.setText(joke);
    }
}
