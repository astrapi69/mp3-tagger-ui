package io.github.astrapi69.jaudiotagger;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

public class Mp3CoverInserter
{
	public static void main(String[] args) throws Exception
	{
		File mp3File = new File("song.mp3");
		File imageFile = new File("cover.jpg");

		AudioFile audioFile = AudioFileIO.read(mp3File);
		Tag tag = audioFile.getTagOrCreateAndSetDefault();

		// Optional: vorhandenes Cover entfernen
		tag.deleteArtworkField();

		// Neues Cover erstellen und setzen
		Artwork artwork = ArtworkFactory.createArtworkFromFile(imageFile);
		tag.setField(artwork);

		// Optional: weitere Felder setzen
		tag.setField(FieldKey.ALBUM, "My Album");
		tag.setField(FieldKey.ARTIST, "My Artist");

		// Speichern
		audioFile.commit();
	}
}
