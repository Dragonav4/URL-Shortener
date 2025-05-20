package org.example.s31722tpo10.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ErrorMapper {
    private ErrorMapper() {
    }

    public static ResponseEntity<Void> map(DeleteRes res) {
        return switch (res) {
            case Ok -> ResponseEntity.noContent().build();
            case NOT_FOUND -> ResponseEntity.notFound().build();
            case WRONG_PASSWORD -> forbidden();
        };
    }

    public static ResponseEntity<Void> map(UpdateRes res) {
        return switch (res) {
            case OK -> ResponseEntity.noContent().build();
            case NOT_FOUND -> ResponseEntity.notFound().build();
            case WRONG_PASSWORD -> forbidden();
        };
    }

    private static ResponseEntity<Void> forbidden() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .header("reason", "wrong password")
                .build();
    }
}