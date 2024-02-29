package com.example.myapplication;

import static org.junit.Assert.assertFalse;

import com.example.myapplication.model.SpriteSelector;
import com.example.myapplication.viewmodel.PlayerViewModel;

import org.junit.Before;
import org.junit.Test;

public class NameTest {
    private PlayerViewModel playerViewModel;

    @Test
    public void nullNameCheck() {
        assertFalse(playerViewModel.getNameVM().equals(""));
    }

    public void whiteSpaceCheck() {
        assertFalse(playerViewModel.getNameVM().equals("    "));
    }
}
