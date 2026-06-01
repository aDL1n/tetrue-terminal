package io.github.bfur64.terminal.interfaces;

import io.github.bfur64.terminal.input.KeyStroke;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.Closeable;

@NullMarked
public interface InputHandler extends Closeable {
    void start();
    KeyStroke readInput();

    @Nullable KeyStroke pollInput();
}