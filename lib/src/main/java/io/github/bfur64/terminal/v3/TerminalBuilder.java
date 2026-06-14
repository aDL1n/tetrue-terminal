package io.github.bfur64.terminal.v3;

import io.github.bfur64.terminal.v3.interfaces.TerminalRuntime;
import io.github.bfur64.terminal.v3.jline.JLineRuntime;
import io.github.bfur64.terminal.v3.lanterna.LanternaRuntime;
import io.github.bfur64.terminal.v3.mock.MockRuntime;
import io.github.bfur64.terminal.v3.pipeline.RenderType;
import org.jspecify.annotations.NullMarked;

import java.io.IOException;

@NullMarked
public class TerminalBuilder {
    private RuntimeType runtimeType = RuntimeType.JLINE;
    private RenderType renderType = RenderType.IMMEDIATE;
    private int xSize;
    private int ySize;
    private boolean sizeOverride;

    public TerminalBuilder auto() {
        if (isTermux()) {
            return lanterna();
        }
        else {
            return jline();
        }
    }

    private static boolean isTermux() {
        String prefix = System.getenv("PREFIX");

        return (prefix != null &&
                prefix.contains("termux")) ||
                System.getenv("TERMUX_VERSION") != null;
    }

    public TerminalBuilder jline() {
        this.runtimeType = RuntimeType.JLINE;
        return this;
    }

    public TerminalBuilder lanterna() {
        this.runtimeType = RuntimeType.LANTERNA;
        return this;
    }

    public TerminalBuilder mock() {
        this.runtimeType = RuntimeType.MOCK;
        return this;
    }

    public TerminalBuilder immediate() {
        this.renderType = RenderType.IMMEDIATE;
        return this;
    }

    public TerminalBuilder buffered() {
        this.renderType = RenderType.BUFFERED;
        return this;
    }

    public TerminalBuilder size(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.sizeOverride = true;
        return this;
    }

    public TerminalRuntime build() throws IOException {
        TerminalConfig config = new TerminalConfig(renderType, xSize, ySize, sizeOverride);

        return switch (runtimeType) {
            case JLINE -> new JLineRuntime(config);
            case LANTERNA -> new LanternaRuntime(config);
            case MOCK -> new MockRuntime(config);
        };
    }
}
