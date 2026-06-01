package io.github.bfur64.terminal.interfaces;

import org.jspecify.annotations.NullMarked;

@NullMarked
public interface TerminalBackend extends RendererHandler, InputHandler {
    void start();
    String getTerminalInfo();
}
