/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.keys

/**
 * Base class for verse-location keys used to index parsed Vedic text.
 *
 * Each Vedic layer (Samhita, Brahmana, Aranyaka, Upanishad) uses a different
 * hierarchical numbering scheme. Subclasses encode the specific fields for their
 * layer and implement [Comparable] so that verses can be sorted in canonical order.
 *
 * @param T the concrete key type (self-referential for [Comparable])
 */
abstract class BaseKey<T> : Comparable<T> {
}
