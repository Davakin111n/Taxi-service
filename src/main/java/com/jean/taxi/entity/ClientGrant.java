package com.jean.taxi.entity;

public class ClientGrant extends Identifier {
    private Long clientId;
    private boolean admin = false;
    private boolean active = false;
    private boolean moderator = false;

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientGrant that = (ClientGrant) o;

        if (admin != that.admin) return false;
        if (active != that.active) return false;
        if (moderator != that.moderator) return false;
        return clientId.equals(that.clientId);

    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + (admin ? 1 : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (moderator ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientGrant{" +
                "clientId=" + clientId +
                ", admin=" + admin +
                ", active=" + active +
                ", moderator=" + moderator +
                '}';
    }
}
