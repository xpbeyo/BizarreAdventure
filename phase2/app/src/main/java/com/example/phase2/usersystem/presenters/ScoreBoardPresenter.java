package com.example.phase2.usersystem.presenters;

import android.widget.TextView;

import com.example.phase2.appcore.scoreboard.SortStrategy;
import com.example.phase2.usersystem.models.ScoreBoardModel;
import com.example.phase2.usersystem.views.iview.ITextStringView;

import java.util.ArrayList;
import java.util.Map;

public class ScoreBoardPresenter {
    private ScoreBoardModel scoreBoardModel;
    private ITextStringView iTextStringView;

    public ScoreBoardPresenter(ScoreBoardModel scoreBoardModel, ITextStringView iTextStringView) {
        this.scoreBoardModel = scoreBoardModel;
        this.iTextStringView = iTextStringView;
    }

    /**
     * Below methods show the first to the fifth on the scoreboard.
     */
    public void showFirst(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        System.out.println(result.size());
        if (result.size() >= 1) {
            iTextStringView.setText(textView, result.get(0).getKey() + ": " + result.get(0).getValue());
        }
    }

    public void showSecond(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 2) {
            iTextStringView.setText(textView, result.get(1).getKey() + ": " + result.get(1).getValue());
        }
    }

    public void showThird(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 3) {
            iTextStringView.setText(textView, result.get(2).getKey() + ": " + result.get(2).getValue());
        }
    }

    public void showFourth(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 4) {
            iTextStringView.setText(textView, result.get(3).getKey() + ": " + result.get(3).getValue());
        }
    }

    public void showFifth(TextView textView, SortStrategy sortStrategy) {
        ArrayList<Map.Entry<String, Integer>> result = scoreBoardModel.sortResult(sortStrategy);
        if (result.size() >= 5) {
            iTextStringView.setText(textView, result.get(4).getKey() + ": " + result.get(4).getValue());
        }
    }
}
