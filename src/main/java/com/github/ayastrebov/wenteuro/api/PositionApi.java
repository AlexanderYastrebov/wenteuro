package com.github.ayastrebov.wenteuro.api;

import com.github.ayastrebov.wenteuro.model.Position;
import java.util.List;

public interface PositionApi {

    List<Position> getSuggestions(String text);

}
