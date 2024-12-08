<?php

declare(strict_types=1);

namespace App\Http\Enums;

enum StatusEnum: string
{
    case PENDING = 'pending';
    case IN_PROGRESS = 'in_progress';
    case COMPLETED = 'completed';
    case ARCHIVED = 'archived';

    /**
     * Get a human-readable label for each status.
     */
    public function label(): string
    {
        return match($this)
        {
            self::PENDING => 'Pending',
            self::IN_PROGRESS => 'In Progress',
            self::COMPLETED => 'Completed',
            self::ARCHIVED => 'Archived'
        };
    }

    /**
     * Return all status values as an array
     */
    public static function values(): array
    {
        return array_column(self::cases(), 'value');
    }

    /**
     * Return all status labels as an array
     */
    public static function labels(): array
    {
        return array_map(fn($status) => $status->label(), self::cases());
    }
}
