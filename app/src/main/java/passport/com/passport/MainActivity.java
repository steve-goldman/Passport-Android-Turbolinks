package passport.com.passport;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.basecamp.turbolinks.TurbolinksAdapter;
import com.basecamp.turbolinks.TurbolinksSession;
import com.basecamp.turbolinks.TurbolinksView;

public class MainActivity extends AppCompatActivity implements TurbolinksAdapter
{
    private static final String INTENT_URL_KEY = "intentUrl";

    private String              location;
    private TurbolinksView      turbolinksView;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turbolinksView = (TurbolinksView) findViewById(R.id.turbolinks_view);

        TurbolinksSession.getDefault(this).setDebugLoggingEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        location = getIntent().getStringExtra(INTENT_URL_KEY);
        if (location == null)
        {
            location = getString(R.string.root_url);
        }
        else
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TurbolinksSession.getDefault(this)
                .activity(this)
                .adapter(this)
                .view(turbolinksView)
                .visit(location);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        TurbolinksSession.getDefault(this)
                .activity(this)
                .adapter(this)
                .restoreWithCachedSnapshot(true)
                .view(turbolinksView)
                .visit(location);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
            {
                if (NavUtils.getParentActivityIntent(this) != null)
                {
                    NavUtils.navigateUpFromSameTask(this);
                }
                else
                {
                    finish();
                }

                return true;
            }
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onPageFinished()
    {
        // TODO
    }

    @Override
    public void onReceivedError(int errorCode)
    {
        // TODO
    }

    @Override
    public void pageInvalidated()
    {
        // TODO
    }

    @Override
    public void requestFailedWithStatusCode(int statusCode)
    {
        // TODO
    }

    @Override
    public void visitCompleted()
    {
        // TODO
    }

    @Override
    public void visitProposedToLocationWithAction(String location, String action)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(INTENT_URL_KEY, location);
        startActivity(intent);
    }
}
