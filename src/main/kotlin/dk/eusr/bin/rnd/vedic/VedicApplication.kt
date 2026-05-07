/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

import dk.eusr.bin.rnd.vedic.loader.ShrutisLoad
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

/**
 * Entry point for the Vedic Text Research application.
 *
 * Parses ITX-encoded (Roman-transliterated) Vedic source HTML files from
 * SanskritDocuments.org and produces:
 *   - A word-frequency lexicon (lexicon.txt)
 *   - The full parsed text keyed by verse location (vedas.txt)
 *   - A log of lines that matched a Vedic marker but could not be fully parsed (unparsed.txt)
 *   - Suggested patch entries for unparseable lines (vedas-patches.txt)
 *
 * Currently processes the four Samhita layers: Rigveda, Yajurveda (Maitrayani),
 * Samaveda, and Atharvaveda.
 */
@SpringBootApplication
class VedicApplication : ApplicationRunner {
    /** Directory where output files are written. Override in tests to use a temp directory. */
    var outputDir: Path = FileSystems.getDefault().getPath("output")

    /**
     * Loads the Shruti (Samhita) texts, builds a lexicon, and writes all output
     * files to [outputDir].
     */
    override fun run(args: ApplicationArguments) {
        val shrutisLoader = ShrutisLoad()
        shrutisLoader.load()

        println("${shrutisLoader.text.size} lines read")

        val lexicon = Utils.generateLexicon(shrutisLoader.text)
        println("${lexicon.size} unique words read of ${Utils.totalWords(lexicon)} total")

        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir)
        }

        Utils.writeLexicon(outputDir.resolve("lexicon.txt"), lexicon)
        Utils.writeUnparsed(outputDir.resolve("unparsed.txt"), shrutisLoader.unparsed)
        Utils.writeText(outputDir.resolve("vedas.txt"), shrutisLoader.text)
        Utils.writePatches(outputDir.resolve("vedas-patches.txt"), shrutisLoader.unparsed)
    }
}

fun main(args: Array<String>) {
    runApplication<VedicApplication>(*args)
}
