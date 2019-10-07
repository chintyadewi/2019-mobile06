package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.ac.polinema.idealbodyweight.fragments.AboutFragment;
import id.ac.polinema.idealbodyweight.fragments.BodyMassIndexFragment;
import id.ac.polinema.idealbodyweight.fragments.BrocaIndexFragment;
import id.ac.polinema.idealbodyweight.fragments.MenuFragment;
import id.ac.polinema.idealbodyweight.fragments.ResultFragment;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener,
		BrocaIndexFragment.OnFragmentInteractionListener, ResultFragment.OnFragmentInteractionListener,
		BodyMassIndexFragment.OnFragmentInteractionListener {

	// Deklarasikan atribut Fragment di sini
	private AboutFragment aboutFragment;
	private MenuFragment menuFragment;
    private BrocaIndexFragment brocaIndexFragment;
	private BodyMassIndexFragment bodyMassIndexFragment;
	private ResultFragment resultFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		aboutFragment = AboutFragment.newInstance("Chintya Puspa Dewi");
		menuFragment=new MenuFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, menuFragment)
				.commit();

        brocaIndexFragment = new BrocaIndexFragment();
        bodyMassIndexFragment=new BodyMassIndexFragment();
		resultFragment = new ResultFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return  true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		// TODO: Tambahkan penanganan menu di sini

		if (item.getItemId() == R.id.menu_about) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, aboutFragment)
					.addToBackStack(null)
					.commit();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
    public void onBrocaIndexButtonClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, brocaIndexFragment)
                .commit();
    }

	@Override
	public void onBodyMassIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, bodyMassIndexFragment)
				.commit();
	}

    @Override
    public void onCalculateBrocaIndexClicked(float index) {
		resultFragment.setInformation(String.format("Your ideal weight is %.2f kg", index));
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment, "BrocaIndex")
				.commit();
    }

	@Override
	public void onTryAgainButtonClicked(String tag) {
	    if (tag.equals("BrocaIndex")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, brocaIndexFragment)
                    .commit();
        }else if (tag.equals("BodyMassIndex")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, bodyMassIndexFragment)
                    .commit();
        }
	}

	@Override
	public void onCalculateBodyMassIndexClicked(float index) {
	    String detail="";
	    if (index>=0 && index<18.50){
	        detail="Underweight";
        }else if (index>=18.50 && index<=24.99){
	        detail="Normal";
        }else if (index>25.00){
	        detail="Overweight";
        }else if (index>=25.00 && index<=29.99){
	        detail="Pre-obese";
        }else if (index>=30.00 && index<=34.99){
	        detail="Obese";
        }

		resultFragment.setInformation(String.format("Your body mass index is %.2f kg", index)+" \nDetail: "+detail);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment, "BodyMassIndex")
				.commit();
	}
}
