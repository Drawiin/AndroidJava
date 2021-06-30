package com.drawiin.forca.ui.add_words;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drawiin.forca.R;
import com.drawiin.forca.database.DatabaseHelper;
import com.drawiin.forca.database.WordsDao;
import com.drawiin.forca.utils.ResourceLocator;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddWordsViewModel extends ViewModel {
    private final DatabaseHelper dbHelper;
    private final ResourceLocator resourceLocator;

    @Inject
    AddWordsViewModel(DatabaseHelper dbHelper, ResourceLocator resourceLocator) {
        this.dbHelper = dbHelper;
        this.resourceLocator = resourceLocator;
        loadWords();
    }

    private final MutableLiveData<List<String>> _words = new MutableLiveData<>();
    final LiveData<List<String>> words = _words;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    final LiveData<String> error = _error;

    private final MutableLiveData<String> _success = new MutableLiveData<>();
    final LiveData<String> success = _success;

    private void loadWords() {
        try {
            final SQLiteDatabase db = dbHelper.getReadableDatabase();
            final List<String> words = WordsDao.getWords(db);
            _words.setValue(words);
        } catch (Exception e) {
            _error.setValue(e.toString());
        }
    }

    void onAddWordsClicked(String word) {
        final String value = word.trim();
        if (value.isEmpty()) {
            _error.setValue(resourceLocator.getString(R.string.error_empty_word));
        } else {
            try {
                final SQLiteDatabase db = dbHelper.getReadableDatabase();
                WordsDao.insert(db, value);
                _success.setValue(resourceLocator.getString(R.string.success_word_added));
                loadWords();
            } catch (Error e) {
                _error.setValue(e.getMessage());
            }
        }
    }


    @Override
    protected void onCleared() {
        dbHelper.close();
        super.onCleared();
    }
}
