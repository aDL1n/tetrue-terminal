package io.github.bfur64.terminal.v3.mock;

import io.github.bfur64.terminal.v3.PipelineType;
import io.github.bfur64.terminal.v3.Terminal;
import io.github.bfur64.terminal.v3.TerminalConfig;
import io.github.bfur64.terminal.v3.interfaces.TerminalEnvironment;
import io.github.bfur64.terminal.v3.interfaces.TerminalRuntime;
import io.github.bfur64.terminal.v3.pipeline.BufferedPipeline;
import io.github.bfur64.terminal.v3.pipeline.ImmediatePipeline;
import io.github.bfur64.terminal.v3.pipeline.Pipeline;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class MockRuntime implements TerminalRuntime, TerminalEnvironment {
    private static final int DEFAULT_X = 0;
    private static final int DEFAULT_Y = 0;

    private final Terminal terminal;
    private final int xSize;
    private final int ySize;

    public MockRuntime(TerminalConfig config) {
        PipelineType pipelineType = config.pipelineType();

        Pipeline pipeline = pipelineType == PipelineType.BUFFERED ?
            new BufferedPipeline(new MockBackend()) :
            new ImmediatePipeline(new MockBackend());

        this.terminal = new Terminal(pipeline, new MockInputSource());

        int xSize = DEFAULT_X;
        int ySize = DEFAULT_Y;

        if (config.sizeOverride()) {
            xSize = config.xSize();
            ySize = config.ySize();
        }

        this.xSize = xSize;
        this.ySize = ySize;
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
}
