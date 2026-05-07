/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

/**
 * Base class for Vedic text loaders.
 *
 * Each concrete loader handles one Vedic layer (Samhita, Brahmana, Aranyaka, Upanishad)
 * and is responsible for reading, parsing, and patching the ITX HTML source files for
 * that layer.
 */
abstract class BaseLoader
