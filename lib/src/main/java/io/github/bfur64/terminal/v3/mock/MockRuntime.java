package io.github.bfur64.terminal.v3.mock;

import io.github.bfur64.terminal.v3.pipeline.RenderType;
import io.github.bfur64.terminal.v3.Terminal;
import io.github.bfur64.terminal.v3.interfaces.TerminalEnvironment;
import io.github.bfur64.terminal.v3.interfaces.TerminalRuntime;
import io.github.bfur64.terminal.v3.pipeline.BufferedMode;
import io.github.bfur64.terminal.v3.pipeline.ImmediateMode;
import io.github.bfur64.terminal.v3.pipeline.RenderStrategy;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class MockRuntime implements TerminalRuntime, TerminalEnvironment {
    private final Terminal terminal;
    private int xSize;
    private int ySize;

    public MockRuntime(RenderType renderType) {
        RenderStrategy renderStrategy = renderType == RenderType.BUFFERED ?
            new BufferedMode(new MockBackend()) :
            new ImmediateMode(new MockBackend());

        this.terminal = new Terminal(this, renderStrategy, new MockInputSource());
    }

    @Override
    public Terminal terminal() {
        return terminal;
    }

    @Override
    public void close() {}

    @Override
    public int xSize() {
        return xSize;
    }

    @Override
    public int ySize() {
        return ySize;
    }

    @Override
    public String terminalInfo() {
        return "Mock Terminal";
    }
}
