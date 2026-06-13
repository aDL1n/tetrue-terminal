package io.github.bfur64.terminal.v3;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record TerminalConfig(PipelineType pipelineType, int xSize, int ySize, boolean sizeOverride) {}
