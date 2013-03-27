// GuiFunctionalTest.java

package com.fip.conf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.JOptionPaneFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JOptionPaneFixture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Description
 *
 * @author Fabien Ipseiz
 *
 */
public class GUIFunctionalTest {
	private FrameFixture window;
	private DirectoryPair model;
	
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeMethod
	public void setUp() {
		model = new DirectoryPair("","");

		DirectoryConfigView view = GuiActionRunner
				.execute(new GuiQuery<DirectoryConfigView>() {
					protected DirectoryConfigView executeInEDT() {
						return new DirectoryConfigView(model);
					}
				});

		@SuppressWarnings("unused")
		DirectoryConfigController controller = new DirectoryConfigController(model, view);
	
		window = new FrameFixture(view);
		window.show(); // shows the frame to test
	}

	@AfterMethod 
	public void tearDown() {
	    window.cleanUp();
	  }

	@Test
	public void shouldAddNewSrcDirPathInModelWhenClickingAddButton() {
		final String dir = "D:\\Documents and Settings\\IPSEIZ\\My Documents\\Temp\\Source";
		final Path dirPath = Paths.get(dir);
		
		// check that the model content is empty
		Assert.assertEquals(model.getSrc(),"");
		Assert.assertFalse(Files.exists(dirPath),"Source directory already exist!");
		// TODO check whether the source directory exist, if Yes => delete it
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory");
		Assert.assertEquals(fixture.textBox("directory").text(),dir);
		fixture.buttonWithText("OK").click();
		
		// check the model content
		Assert.assertEquals(model.getSrc(),dir);
		// check whether the source directory has been created
		Assert.assertTrue(Files.exists(dirPath),"Source directory has not been created!");
	}
	
	@Test
	public void shouldAddExistingSrcDirPathInModelWhenClickingAddButton() {
		final String dir = "D:\\Documents and Settings\\IPSEIZ\\My Documents\\Temp\\Source";
		Path folder = Paths.get(dir);
		
		// check that the model content is empty
		Assert.assertEquals(model.getSrc(),"");
		Assert.assertTrue(Files.exists(folder),"Source directory does not exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		Assert.assertEquals(model.getSrc(),dir,"model content is not correct");
	}
	@Test
	public void shouldAddNewTgtDirPathInModelWhenClickingAddButton() {
		final String dir = "D:\\Documents and Settings\\IPSEIZ\\My Documents\\Temp\\Target";
		final Path dirPath = Paths.get(dir);
		
		Assert.assertEquals(model.getTgt(),"","model content is not empty");
		Assert.assertFalse(Files.exists(dirPath),"Source directory already exist!");
		// TODO check whether the source directory exist, if Yes => delete it
		
		window.button("OK").requireEnabled();
		window.textBox("tgtElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory");
		Assert.assertEquals(fixture.textBox("directory").text(),dir);
		fixture.buttonWithText("OK").click();
		
		Assert.assertEquals(model.getTgt(),dir,"model content is not correct");
		Assert.assertTrue(Files.exists(dirPath),"Source directory has not been created!");
	}
	
	@Test
	public void shouldAddExistingTgtDirPathInModelWhenClickingAddButton() {
		final String dir = "D:\\Documents and Settings\\IPSEIZ\\My Documents\\Temp\\Target";
		Path folder = Paths.get(dir);
		
		// check that the model content is empty
		Assert.assertEquals(model.getSrc(),"");
		Assert.assertTrue(Files.exists(folder),"Source directory does not exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("tgtElement").enterText(dir);
		window.button("OK").click();
		
		Assert.assertEquals(model.getTgt(),dir,"model content is not correct");
	}
	
	@Test
	public void shouldCancelAddNewSrcDirPathInModelWhenClickingAddThenCancelButtons() {
		final String dir = "D:\\Documents and Settings\\IPSEIZ\\Cancel";
		final Path dirPath = Paths.get(dir);
		
		// check that the model content is empty
		Assert.assertEquals(model.getSrc(),"");
		Assert.assertFalse(Files.exists(dirPath),"Source directory already exist!");
		// TODO check whether the source directory exist, if Yes => delete it
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory");
		Assert.assertEquals(fixture.textBox("directory").text(),dir);
		fixture.buttonWithText("Annuler").click();
		
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertTrue(Files.notExists(dirPath),"Source directory has been created!");
	}
	
	@Test
	public void shouldExitFrameWhenPressingCancel() {
		window.button("Cancel").requireEnabled();
		window.button("Cancel").click();
		// TODO check that the frame is closed
	}
	
}
