package com.soen.riskgame.module.core.interfaces;

import javafx.scene.Scene;

import java.io.IOException;

/**
 * Interface View to support the functionality of view
 * 
 */
public interface View {


	/**
	 * method getView to return the view
	 * @return object of Scene
	 * @throws IOException
	 */
    Scene getView() throws IOException;




}
