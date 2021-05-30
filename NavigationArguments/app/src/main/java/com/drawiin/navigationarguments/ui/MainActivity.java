package com.drawiin.navigationarguments.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigationarguments.R;
import com.drawiin.navigationarguments.databinding.ActivityMainBinding;
import com.drawiin.navigationarguments.model.Game;
import com.drawiin.navigationarguments.model.Gender;
import com.drawiin.navigationarguments.model.Modes;
import com.drawiin.navigationarguments.model.Platform;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupUi();
    }

    private void setupUi() {
        setupDatePicker();
        setupGenders();
        setupPlatforms();

        binding.btnRegister.setOnClickListener((view) -> onRegister());
    }

    private void setupDatePicker() {

        Objects.requireNonNull(binding.launch.getEditText()).setOnClickListener((view) -> {
            final MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Data de Lançamento")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.addOnPositiveButtonClickListener((timeInMillis) -> {
                {
                    final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    calendar.setTimeInMillis((Long) timeInMillis);
                    final Date date = calendar.getTime();
                    final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    final String result = formatter.format(date);
                    Objects.requireNonNull(binding.launch.getEditText()).setText(result);
                }
            });
            datePicker.show(getSupportFragmentManager(), "Picker");
        });
    }

    private void setupGenders() {
        final String[] genders = Arrays.stream(Gender.values())
                .map(Gender::getName)
                .toArray(String[]::new);
        final ArrayAdapter<TextView> genderAdapter = new ArrayAdapter(this, R.layout.list_item, genders);
        ((AutoCompleteTextView) Objects.requireNonNull(binding.gender.getEditText())).setAdapter(genderAdapter);
    }

    private void setupPlatforms() {
        final String[] platforms = Arrays.stream(Platform.values())
                .map(Platform::getName)
                .toArray(String[]::new);
        final ArrayAdapter<TextView> platformAdapter = new ArrayAdapter(this, R.layout.list_item, platforms);
        ((AutoCompleteTextView) Objects.requireNonNull(binding.platform.getEditText())).setAdapter(platformAdapter);
    }

    private void onRegister() {
        final String title = getInputValue(binding.title);
        final String producer = getInputValue(binding.producer);
        final String launch = getInputValue(binding.launch);
        final Gender gender = getSelectedGender();
        final Platform platform = getSelectedPlatform();
        final Modes[] modes = getSelectedModes();

        final Game game = new Game(title, gender, platform, modes, producer, launch, "");
        ActivityGameDetails.navigate(this, game);
    }

    private String getInputValue(TextInputLayout input) {
        return Objects.requireNonNull(input.getEditText()).getText().toString();
    }

    private Gender getSelectedGender() {
        final String genderString = getInputValue(binding.gender);
        final Optional<Gender> optionalGender = Arrays.stream(Gender.values())
                .filter(g -> g.getName().equalsIgnoreCase(genderString))
                .findFirst();
        return optionalGender.orElse(Gender.BATTLE_ROYALE);
    }

    private Platform getSelectedPlatform() {
        final String platformString = getInputValue(binding.platform);
        final Optional<Platform> optionalPlatform = Arrays.stream(Platform.values())
                .filter(g -> g.getName().equalsIgnoreCase(platformString))
                .findFirst();
        return optionalPlatform.orElse(Platform.ANDROID);
    }

    private Modes[] getSelectedModes() {
        Map<Integer, Modes> modesMap = new HashMap<>();
        modesMap.put(R.id.single_player, Modes.SINGLE);
        modesMap.put(R.id.multiplayer, Modes.MULTI);

        return Arrays.stream(new CheckBox[]{binding.singlePlayer, binding.multiplayer}).
                filter(CompoundButton::isChecked)
                .map(box -> modesMap.get(box.getId()))
                .toArray(Modes[]::new);
    }
}