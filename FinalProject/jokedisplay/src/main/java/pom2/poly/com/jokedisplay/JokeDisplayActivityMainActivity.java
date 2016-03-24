package pom2.poly.com.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.JokeTeller;

public class JokeDisplayActivityMainActivity extends AppCompatActivity {

    private TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_display_activity_main);
        tvJoke = (TextView) findViewById(R.id.tvJoke);
        String joke = new JokeTeller().getJoke();
        tvJoke.setText(joke);
    }
}
