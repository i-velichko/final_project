package org.velichko.finalproject.logic.entity;

import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;

import java.sql.Blob;

/**
 * @author Ivan Velichko
 *
 * The type User.
 */
public class User extends Entity {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String gitLink;
    private UserStatus status;
    private UserRole role;
    private Blob image;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param login     the login
     * @param email     the email
     * @param role      the role
     * @param status    the status
     */
    public User(String firstName, String lastName, String login,  String email, UserRole role, UserStatus status) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param studentName the student name
     * @param surename    the surename
     */
    public User(String studentName,String surename) {
        this.firstName = studentName;
        this.lastName = surename;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets git link.
     *
     * @return the git link
     */
    public String getGitLink() {
        return gitLink;
    }

    /**
     * Sets git link.
     *
     * @param gitLink the git link
     */
    public void setGitLink(String gitLink) {
        this.gitLink = gitLink;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getId() != user.getId()) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null) {
            return false;
        }
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        if (getGitLink() != null ? !getGitLink().equals(user.getGitLink()) : user.getGitLink() != null) {
            return false;
        }
        if (getStatus() != user.getStatus()) {
            return false;
        }
        return getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getGitLink() != null ? getGitLink().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", gitLink='").append(gitLink).append('\'');
        sb.append(", status=").append(status);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
