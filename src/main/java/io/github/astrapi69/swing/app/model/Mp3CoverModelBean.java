package io.github.astrapi69.swing.app.model;

import java.io.File;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class {@code Mp3CoverModelBean} holds data for the
 * {@link io.github.astrapi69.swing.app.panel.Mp3CoverPanel}
 */
@Data
@NoArgsConstructor
public class Mp3CoverModelBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String mp3FilePath;
	String title;
	String artist;
	String album;
	File coverFile;

	@Builder
	public Mp3CoverModelBean(String mp3FilePath, String title, String artist, String album,
		File coverFile)
	{
		this.mp3FilePath = mp3FilePath;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.coverFile = coverFile;
	}
}
