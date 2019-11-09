package com.soen.riskgame.module.core;

import com.soen.riskgame.module.core.command_line.LexerTest;
import com.soen.riskgame.module.core.model.CardTest;
import com.soen.riskgame.module.core.model.MapDataTest;
import com.soen.riskgame.module.core.model.PlayerTest;
import com.soen.riskgame.module.core.utils.GraphUtilTest;
import com.soen.riskgame.module.core.utils.MapValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LexerTest.class, CardTest.class, MapDataTest.class, PlayerTest.class, GraphUtilTest.class, MapValidatorTest.class})
public class TestSuite {
}
