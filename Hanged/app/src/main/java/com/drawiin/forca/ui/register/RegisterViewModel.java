package com.drawiin.forca.ui.register;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drawiin.forca.R;
import com.drawiin.forca.database.DatabaseHelper;
import com.drawiin.forca.database.WordsDao;
import com.drawiin.forca.utils.Constants;
import com.drawiin.forca.utils.ResourceLocator;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private final DatabaseHelper dbHelper;
    private final ResourceLocator resourceLocator;

    @Inject
    RegisterViewModel(DatabaseHelper dbHelper, ResourceLocator resourceLocator) {
        this.dbHelper = dbHelper;
        this.resourceLocator = resourceLocator;
        seedDb();
    }

    private final MutableLiveData<String> _goToGame = new MutableLiveData<>();
    final LiveData<String> goToGame = _goToGame;

    private final MutableLiveData<Boolean> _goToAddWords = new MutableLiveData<>();
    final LiveData<Boolean> goToAddWords = _goToAddWords;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    final LiveData<String> error = _error;

    private void seedDb() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<String> words = WordsDao.getWords(db);
        if (words.isEmpty()) {
            Arrays.stream(Constants.DEFAULT_WORDS)
                    .forEach(word -> WordsDao.insert(db, word));
        }
    }

    public void onPlayClicked(String nickname) {
        final String value = nickname.trim();
        if (value.isEmpty()) {
            _error.setValue(resourceLocator.getString(R.string.error_empty_nickname));
        } else {
            _goToGame.setValue(value);
        }
    }

    public void onAddWordsClicked() {
        _goToAddWords.setValue(true);
    }
}
