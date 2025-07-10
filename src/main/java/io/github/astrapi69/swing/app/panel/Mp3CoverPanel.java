package io.github.astrapi69.swing.app.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.app.model.Mp3CoverModelBean;
import io.github.astrapi69.swing.base.BasePanel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * The class {@code Mp3CoverPanel} represents a panel to set MP3 tags and a cover image
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mp3CoverPanel extends BasePanel<Mp3CoverModelBean>
{
	JButton btnSelectMp3;
	JButton btnSelectCover;
	JButton btnSave;

	JTextField txtSelectedFile;
	JTextField txtTitle;
	JTextField txtArtist;
	JTextField txtAlbum;

	JLabel lblCoverPreview;

	public Mp3CoverPanel(final IModel<Mp3CoverModelBean> model)
	{
		super(model);
	}

	public Mp3CoverPanel()
	{
		this(BaseModel.of(Mp3CoverModelBean.builder().build()));
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		btnSelectMp3 = new JButton("Select MP3 File");
		btnSelectCover = new JButton("Select Cover Image");
		btnSave = new JButton("Save Tags");

		txtSelectedFile = new JTextField();
		txtTitle = new JTextField();
		txtArtist = new JTextField();
		txtAlbum = new JTextField();
		lblCoverPreview = new JLabel("No image selected", SwingConstants.CENTER);

		txtSelectedFile.setEditable(false);

		btnSelectMp3.addActionListener(this::onSelectMp3);
		btnSelectCover.addActionListener(this::onSelectCover);
		btnSave.addActionListener(this::onSaveTags);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		this.setLayout(new BorderLayout(5, 5));

		JPanel topPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		topPanel.add(new JLabel("MP3 File:"));
		topPanel.add(txtSelectedFile);
		topPanel.add(new JLabel("Title:"));
		topPanel.add(txtTitle);
		topPanel.add(new JLabel("Artist:"));
		topPanel.add(txtArtist);
		topPanel.add(new JLabel("Album:"));
		topPanel.add(txtAlbum);
		topPanel.add(btnSelectMp3);
		topPanel.add(btnSelectCover);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(lblCoverPreview, BorderLayout.CENTER);
		this.add(btnSave, BorderLayout.SOUTH);
	}

	private void onSelectMp3(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser
			.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("MP3 files", "mp3"));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File mp3 = fileChooser.getSelectedFile();
			txtSelectedFile.setText(mp3.getAbsolutePath());
			getModelObject().setMp3FilePath(mp3.getAbsolutePath());
		}
	}

	private void onSelectCover(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files",
			"jpg", "jpeg", "png"));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File image = fileChooser.getSelectedFile();
			getModelObject().setCoverFile(image);
			lblCoverPreview.setIcon(new ImageIcon(new ImageIcon(image.getAbsolutePath()).getImage()
				.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			lblCoverPreview.setText(null);
		}
	}

	private void onSaveTags(ActionEvent e)
	{
		try
		{
			File mp3 = new File(getModelObject().getMp3FilePath());
			AudioFile audioFile = AudioFileIO.read(mp3);
			Tag tag = audioFile.getTagOrCreateAndSetDefault();

			tag.setField(FieldKey.TITLE, txtTitle.getText());
			tag.setField(FieldKey.ARTIST, txtArtist.getText());
			tag.setField(FieldKey.ALBUM, txtAlbum.getText());

			File cover = getModelObject().getCoverFile();
			if (cover != null)
			{
				tag.deleteArtworkField();
				Artwork artwork = ArtworkFactory.createArtworkFromFile(cover);
				tag.setField(artwork);
			}

			audioFile.commit();
			JOptionPane.showMessageDialog(this, "MP3 tags saved successfully!");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving tags: " + ex.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}
}
