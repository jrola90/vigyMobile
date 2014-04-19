package pl.jrola.java.android.vigym.vigymobile.activities;

import java.io.File;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import ar.com.daidalos.afiledialog.FileChooserDialog;

public class ImportDataActivity extends AbstractVigymActivity {

	private File chosenFile;
	
	// UI references
	private FileChooserDialog dialog;
	
	private RadioGroup radioButtonsGroup;
	private RadioButton importFromFileRadioButton;
	private RadioButton importFromNetworkRadioButton;
	
	private EditText filePathEditText;
	private Button chooseFileButton;
	
	private EditText loginEditText;
	private EditText passwordEditText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import_data);
		
		this.dialog = new FileChooserDialog(this);
		
		this.radioButtonsGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		this.importFromFileRadioButton = (RadioButton)findViewById(R.id.importFromFileRadioButton);
		this.importFromNetworkRadioButton = (RadioButton)findViewById(R.id.importFromNetworkRadioButton);
		
		this.filePathEditText = (EditText) findViewById(R.id.filePathEditText);
		this.chooseFileButton = (Button)findViewById(R.id.chooseFileButton);
		
		this.loginEditText = (EditText) findViewById(R.id.importLoginEditText);
		this.passwordEditText = (EditText) findViewById(R.id.importPasswordEditText);
		
		disableRadioButton();
		
		this.radioButtonsGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				disableRadioButton();
			}
		});
		
	}

	@Override
	protected void clearForm() {
		
	}

	private void disableRadioButton() {
		if(this.importFromFileRadioButton.isChecked()) {
			this.filePathEditText.setEnabled(true);
			this.chooseFileButton.setEnabled(true);
			this.loginEditText.setEnabled(false);
			this.passwordEditText.setEnabled(false);
		} else {
			this.loginEditText.setEnabled(true);
			this.passwordEditText.setEnabled(true);
			this.filePathEditText.setEnabled(false);
			this.chooseFileButton.setEnabled(false);
		}
	}
	
	public void choseFileButtonClick(View view) {

		dialog.addListener(new FileChooserDialog.OnFileSelectedListener() {
			public void onFileSelected(Dialog source, File file) {
				source.hide();
				Toast toast = Toast.makeText(source.getContext(),
						getString(R.string.import_chosen_file) + file, Toast.LENGTH_LONG);
				toast.show();
				chosenFile = file;
				filePathEditText.setText(file.getAbsolutePath());
				disableRadioButton();
			}

			public void onFileSelected(Dialog source, File folder, String name) {
			}
		});

		dialog.show();

	}
	
	public void okButtonOnClick(View view) {
		
	}
	
	public void cancelButtonOnClick(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);		
	}
}