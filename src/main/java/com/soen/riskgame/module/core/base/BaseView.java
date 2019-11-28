package com.soen.riskgame.module.core.base;

import com.soen.riskgame.module.core.interfaces.View;
import javafx.scene.Scene;

import java.io.IOException;
/**
 * Base view class this class implements view
 * @author  Sai Sukruth
 *
 */
public class BaseView implements View {
    /**
     * Method in view interface. Will not return <code>null</code>.
     * @return returns the scene view
     * @throws IOException
     */
    @Override
    public Scene getView() throws IOException {
        return null;
    }

}
