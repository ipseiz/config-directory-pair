// GuiFunctionalTest.java

package com.fip.conf;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.JFileChooserFinder;
import org.fest.swing.finder.JOptionPaneFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JFileChooserFixture;
import org.fest.swing.fixture.JOptionPaneFixture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fip.common.DirectoryPair;
import com.fip.conf.FileTools;
import com.fip.dir_selection.DirectoryConfigController;
import com.fip.dir_selection.DirectoryConfigView;


/**
 * Description
 *
 * @author Fabien Ipseiz
 *
 */
public class GUIFunctionalTest {
	private FrameFixture window;
	private DirectoryPair model;

	// 'basedir' is a Maven variable that points to the root directory of the project
	// File basedir specifies the directory that will serve as reference for the use
	// of a relative location of other directories.
	private final File basedir = new File(System.getProperty("basedir", "")).getAbsoluteFile();
	private final File filePath = new File(basedir, "src\\test\\temp");
	private final Path dirPath = Paths.get(filePath.getAbsolutePath());
	private final String dir = dirPath.toString();

	private static final Logger logger = LoggerFactory.getLogger(GUIFunctionalTest.class);
	
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
		
		cleanDir(); // delete test directory if it is exist
	}

	@AfterMethod
	public void tearDown() {
		window.cleanUp();
	}

	@AfterClass
	public void cleanDir() {
		// delete test directory if it is exist
		try {
			FileTools.deleteRecursive(dirPath);
		} catch (Exception e) {
			logger.error("delete error ", e);
		}
	}

	@Test
	public void shouldAddNewSrcDirPathInModelWhenClickingAddButton() {
		// check the initial context				
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertFalse(Files.exists(dirPath),"Source directory already exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory");
		Assert.assertEquals(fixture.textBox("directory").text(),dir);
		fixture.buttonWithText("OK").click();
		
		// check the model content 
		Assert.assertEquals(model.getSrc(),dir);
		Assert.assertTrue(Files.exists(dirPath),"Source directory has not been created!");
	}
	
	@Test
	public void shouldAddExistingSrcDirPathInModelWhenClickingAddButton() {
		try {
			FileTools.createFolder(dir);
		} catch (Exception e) {
			logger.error("directory creation error ", e);
		}
		
		// check the initial context
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertTrue(Files.exists(dirPath),"Source directory does not exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		Assert.assertEquals(model.getSrc(),dir,"model content is not correct");
	}
	@Test
	public void shouldAddNewTgtDirPathInModelWhenClickingAddButton() {
		// check the initial context
		Assert.assertEquals(model.getTgt(),"","model content is not empty");
		Assert.assertFalse(Files.exists(dirPath),"Target directory already exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("tgtElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory");
		Assert.assertEquals(fixture.textBox("directory").text(),dir);
		fixture.buttonWithText("OK").click();
		
		Assert.assertEquals(model.getTgt(),dir,"model content is not correct");
		Assert.assertTrue(Files.exists(dirPath),"Target directory has not been created!");
	}
	
	@Test
	public void shouldAddExistingTgtDirPathInModelWhenClickingAddButton() {
		try {
			FileTools.createFolder(dir);
		} catch (Exception e) {
			logger.error("directory creation error ", e);
		}
		
		// check the initial context
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertTrue(Files.exists(dirPath),"Target directory does not exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("tgtElement").enterText(dir);
		window.button("OK").click();
		
		Assert.assertEquals(model.getTgt(),dir,"model content is not correct");
	}
	
	@Test
	public void shouldCancelAddNewSrcDirPathInModelWhenClickingAddThenCancelButtons() {
		// check the initial context
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertFalse(Files.exists(dirPath),"Source directory already exist!");
		
		window.button("OK").requireEnabled();
		window.textBox("srcElement").enterText(dir);
		window.button("OK").click();
		
		JOptionPaneFixture fixture = JOptionPaneFinder.findOptionPane().using(window.robot);
		Assert.assertEquals(fixture.title(),"Non-existing Directory",dir);
		fixture.buttonWithText("Annuler").click();
		
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertTrue(Files.exists(dirPath),"Source directory has not been created!");
	}
	
	@Test
	public void shouldChangeSrcDirPathInModelWhenClickingChangeButton() {
		try {
			FileTools.createFolder(dir);
		} catch (Exception e) {
			logger.error("directory creation error ", e);
		}
		
		// check the initial context
		Assert.assertEquals(model.getSrc(),"","model content is not empty");
		Assert.assertTrue(Files.exists(dirPath),"Source directory does not exist!");
		
		window.button("Change Src").click();
		
		JFileChooserFixture fixture = JFileChooserFinder.findFileChooser().using(window.robot);
		fixture.selectFile(filePath);
		fixture.approveButton().click();
		
		window.button("OK").click();
		
		Assert.assertEquals(model.getSrc(),dir,"model content is empty");
	}
	
	@Test
	public void shouldChangeTgtDirPathInModelWhenClickingChangeButton() {
		try {
			FileTools.createFolder(dir);
		} catch (Exception e) {
			logger.error("directory creation error ", e);
		}
		
		// check the initial context
		Assert.assertEquals(model.getTgt(),"","model content is not empty");
		Assert.assertTrue(Files.exists(dirPath),"Source directory does not exist!");
		
		window.button("Change Tgt").click();
		
		JFileChooserFixture fixture = JFileChooserFinder.findFileChooser().using(window.robot);
		fixture.selectFile(filePath);
		fixture.approveButton().click();
		
		window.button("OK").click();
		
		Assert.assertEquals(model.getTgt(),dir,"model content is empty");
	}
	
	@Test
	public void shouldExitFrameWhenPressingCancel() {
		window.button("Cancel").requireEnabled();
		window.button("Cancel").click();
		// TODO check that the frame is closed
	}
}
