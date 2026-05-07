# Vedic Text Research

A Kotlin tool for parsing ITX-encoded Vedic source texts and generating lexicons.

## Status

Personal research tool. Not intended for general use, not maintained on any schedule, and published primarily to make the work visible. The code is a work-in-progress and reflects my own learning process.

## What it does

The current parser handles ITX HTML files — the Roman-transliteration encoding format used by [SanskritDocuments.org](https://sanskritdocuments.org/) and similar volunteer-curated Vedic corpora. Given an ITX HTML input, the parser:

- Extracts word tokens and their locations using the chapter/verse/mantra markers embedded in the source
- Produces a lexicon text file mapping each unique word to its location(s)
- Dumps any unparseable fragments to a separate file for inspection

Most of the parsing complexity comes from the variety of verse-marker conventions across the different Vedic layers. The Rigveda uses Mandala/Sukta/Mantra, parts of the Yajurveda use Adhyaya/Kanda, and so on. The current code handles a subset of these conventions; expanding that subset is part of the active work.

## Direction

The longer-term goal is a flat, deduplicated corpus across all four Vedas and their four layers (Samhitas, Brahmanas, Aranyakas, Upanishads), with:

- Each distinct manuscript represented exactly once
- Verbatim quotations from lower layers preserved in upper layers, with metadata pointing back to their original sources
- A generated lexicon mapping each unique word to its locations
- A concordance built on the lexicon
- An eventual transliteral translation pipeline

This is a hobby-scale research project, not a product. The pipeline above is a direction, not a roadmap with dates.

## Source texts

The source texts included in this repository are public-domain Vedic literature obtained from [SanskritDocuments.org](https://sanskritdocuments.org/), a volunteer-curated repository of Sanskrit texts in ITX, Devanagari Unicode, and other formats. The Vedas themselves are public domain — the texts are roughly 3,000 years old. The specific digital encodings are volunteer scholarly work made freely available for personal and academic use.

Specific provenance for each source file is documented in [SOURCES.md](SOURCES.md).

## Building

Standard Gradle workflow. Spring Boot is the runtime framework.

```
./gradlew build
./gradlew test
./gradlew bootRun
```

## License

The Kotlin code in this repository is licensed under the [MIT License](LICENSE).

The included Vedic source texts are public-domain Sanskrit literature; their digital encodings carry the licenses of the upstream contributors at SanskritDocuments.org.
